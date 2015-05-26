package com.gabilheri.octokitten.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gabilheri.octokitten.R;
import com.gabilheri.octokitten.app.PrefManager;
import com.gabilheri.octokitten.data.api.github.GithubService;
import com.gabilheri.octokitten.data_models.LoginRequest;
import com.gabilheri.octokitten.data_models.User;
import com.gabilheri.octokitten.data_models.UserToken;
import com.gabilheri.octokitten.network.BasicCredentialsInterceptor;
import com.gabilheri.octokitten.network.GithubClient;
import com.gabilheri.octokitten.network.TokenInterceptor;
import com.gabilheri.octokitten.ui.DefaultFragment;
import com.gabilheri.octokitten.ui.main.MainActivity;
import com.gabilheri.octokitten.utils.Preferences;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/13/15.
 */
public class SignInFragment extends DefaultFragment<AuthComponent> {

    @InjectView(R.id.username)
    EditText username;

    @InjectView(R.id.password)
    EditText password;

    @InjectView(R.id.btn_sign_in)
    Button btnSignIn;

    @Inject
    Timber.Tree logger;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }

    @OnClick(R.id.btn_sign_in)
    public void signIn() {
        Timber.i("Signing in...");
        if(username.getText().toString().trim().length() > 1 && password.getText().toString().trim().length() > 1) {

            GithubService githubService = new GithubClient(true,
                    new BasicCredentialsInterceptor(
                            username.getText().toString(),
                            password.getText().toString()),
                    getActivity())
                .createGithubService();
            Observable<UserToken> tokenObservable = githubService.signIn(new LoginRequest());
            mCompositeSubscription.add(
                        tokenObservable
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(tokenSubscriber));

            AppObservable.bindFragment(this, tokenObservable);
        }
    }

    public Subscriber<UserToken> tokenSubscriber = new Subscriber<UserToken>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Timber.e(e, "Error signing in!");
        }

        @Override
        public void onNext(UserToken userToken) {
            PrefManager.with(getActivity()).save(Preferences.AUTH_TOKEN, userToken.getToken());
            GithubService service = new GithubClient(true,
                    new TokenInterceptor(getActivity()), getActivity()).createGithubService();
            mCompositeSubscription.add(
                    service.getUser()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(userSubscriber));
        }
    };

    public Subscriber<User> userSubscriber = new Subscriber<User>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(User user) {
            // We successfully got a user!
            PrefManager.with(getActivity()).save(Preferences.USERNAME, user.getLogin());
            PrefManager.with(getActivity()).save(Preferences.EMAIL, user.getEmail());
            PrefManager.with(getActivity()).save(Preferences.AVATAR_URL, user.getAvatarUrl());
            getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
        }
    };

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_sign_in;
    }

    @Override
    protected void inject(AuthComponent component) {
        component.inject(this);
    }
}
