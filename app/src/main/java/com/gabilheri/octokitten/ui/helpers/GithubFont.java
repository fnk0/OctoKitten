package com.gabilheri.octokitten.ui.helpers;

import android.content.Context;
import android.graphics.Typeface;

import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.iconics.typeface.ITypeface;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/13/15.
 */
public class GithubFont implements ITypeface {

    private static final String TTF_FILE = "octicons.ttf";
    private static Typeface typeface = null;
    private static HashMap<String, Character> mChars;

    public GithubFont() {
    }

    @Override
    public IIcon getIcon(String s) {
        return GithubFont.Icon.valueOf(s);
    }

    @Override
    public HashMap<String, Character> getCharacters() {
        if(mChars == null) {
            HashMap aChars = new HashMap();
            GithubFont.Icon[] var2 = GithubFont.Icon.values();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                GithubFont.Icon v = var2[var4];
                aChars.put(v.name(), Character.valueOf(v.character));
            }

            mChars = aChars;
        }

        return mChars;
    }

    @Override
    public String getMappingPrefix() {
        return "gh";
    }

    @Override
    public String getFontName() {
        return "Github";
    }

    @Override
    public String getVersion() {
        return "0.0.0";
    }

    @Override
    public int getIconCount() {
        return mChars.size();
    }

    @Override
    public Collection<String> getIcons() {
        LinkedList icons = new LinkedList();
        GithubFont.Icon[] var2 = GithubFont.Icon.values();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            GithubFont.Icon value = var2[var4];
            icons.add(value.name());
        }

        return icons;
    }

    @Override
    public String getAuthor() {
        return "Github";
    }

    @Override
    public String getUrl() {
        return "https://octicons.github.com/";
    }

    @Override
    public String getDescription() {
        return "Octicons is a font made by github with their icon set";
    }

    @Override
    public String getLicense() {
        return null;
    }

    @Override
    public String getLicenseUrl() {
        return null;
    }

    @Override
    public Typeface getTypeface(Context context) {
        if(typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), TTF_FILE);
            } catch (Exception var3) {
                return null;
            }
        }

        return typeface;
    }

    public static enum Icon implements IIcon {

        gh_git_commit ('\uf01f'),
        gh_readme('\uf007'),
        gh_code('\uf05f'),
        gh_issues('\uf026'),
        gh_people('\uf037'),
        gh_octicon_face('\uf008'),
        gh_repo('\uf001'),
        gh_sign_out('\uf032'),
        gh_sign_in('\uf036'),
        gh_code_file('\uf010'),
        gh_code_binary('\uf094'),
        gh_settings('\uf02f'),
        gh_news('\uf034'),
        gh_star('\uf02a'),
        gh_tools('\uf031'),
        gh_info('\uf059'),
        gh_dashboard('\uf07d');

        char character;
        private static ITypeface typeface;

        private Icon(char character) {
            this.character = character;
        }

        public String getFormattedName() {
            return "{" + this.name() + "}";
        }

        public char getCharacter() {
            return this.character;
        }

        public String getName() {
            return this.name();
        }

        public ITypeface getTypeface() {
            if(typeface == null) {
                typeface = new GithubFont();
            }

            return typeface;
        }
    }
}
