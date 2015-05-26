package com.gabilheri.octokitten.ui.cards;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabilheri.octokitten.R;
import com.gabilheri.octokitten.data_models.User;
import com.gabilheri.octokitten.ui.BaseActivity;
import com.gabilheri.octokitten.ui.user_profile.UserProfileActivity;
import com.squareup.picasso.Picasso;

import butterknife.InjectView;
import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/25/15.
 */
public class CardUser extends Card implements Card.OnCardClickListener {

    @InjectView(R.id.user_picture)
    ImageView userPic;

    @InjectView(R.id.username)
    TextView username;


    private User user;

    public CardUser(Context context, User user) {
        super(context, R.layout.card_user);
        this.user = user;

    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);

        if(user != null) {
            Picasso.with(getContext()).load(user.getAvatarUrl()).fit().into(userPic);
            username.setText(user.getLogin());
        }
    }

    @Override
    public void onClick(Card card, View view) {
        Intent i = new Intent(getContext(), UserProfileActivity.class);
        i.putExtra(BaseActivity.EXTRA_LOGIN, user.getLogin());
        getContext().startActivity(i);
    }
}
