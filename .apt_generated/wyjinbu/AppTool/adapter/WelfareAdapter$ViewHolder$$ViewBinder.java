// Generated code from Butter Knife. Do not modify!
package wyjinbu.AppTool.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class WelfareAdapter$ViewHolder$$ViewBinder<T extends wyjinbu.AppTool.adapter.WelfareAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131034146, "field 'tvTime'");
    target.tvTime = finder.castView(view, 2131034146, "field 'tvTime'");
    view = finder.findRequiredView(source, 2131034145, "field 'mImageView'");
    target.mImageView = finder.castView(view, 2131034145, "field 'mImageView'");
  }

  @Override public void unbind(T target) {
    target.tvTime = null;
    target.mImageView = null;
  }
}
