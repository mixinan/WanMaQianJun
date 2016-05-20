// Generated code from Butter Knife. Do not modify!
package wyjinbu.AppTool.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class RandomAdapter$ViewHolder$$ViewBinder<T extends wyjinbu.AppTool.adapter.RandomAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131034141, "field 'mDesc'");
    target.mDesc = finder.castView(view, 2131034141, "field 'mDesc'");
    view = finder.findRequiredView(source, 2131034142, "field 'mUrl'");
    target.mUrl = finder.castView(view, 2131034142, "field 'mUrl'");
    view = finder.findRequiredView(source, 2131034140, "field 'mType'");
    target.mType = finder.castView(view, 2131034140, "field 'mType'");
    view = finder.findRequiredView(source, 2131034138, "field 'mOther'");
    target.mOther = finder.castView(view, 2131034138, "field 'mOther'");
    view = finder.findRequiredView(source, 2131034139, "field 'mWho'");
    target.mWho = finder.castView(view, 2131034139, "field 'mWho'");
    view = finder.findRequiredView(source, 2131034137, "field 'mImageView'");
    target.mImageView = finder.castView(view, 2131034137, "field 'mImageView'");
  }

  @Override public void unbind(T target) {
    target.mDesc = null;
    target.mUrl = null;
    target.mType = null;
    target.mOther = null;
    target.mWho = null;
    target.mImageView = null;
  }
}
