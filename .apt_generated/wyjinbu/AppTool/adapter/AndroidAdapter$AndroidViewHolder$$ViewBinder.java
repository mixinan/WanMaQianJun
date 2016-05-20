// Generated code from Butter Knife. Do not modify!
package wyjinbu.AppTool.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AndroidAdapter$AndroidViewHolder$$ViewBinder<T extends wyjinbu.AppTool.adapter.AndroidAdapter.AndroidViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131034135, "field 'tvTime'");
    target.tvTime = finder.castView(view, 2131034135, "field 'tvTime'");
    view = finder.findRequiredView(source, 2131034136, "field 'tvDesc'");
    target.tvDesc = finder.castView(view, 2131034136, "field 'tvDesc'");
  }

  @Override public void unbind(T target) {
    target.tvTime = null;
    target.tvDesc = null;
  }
}
