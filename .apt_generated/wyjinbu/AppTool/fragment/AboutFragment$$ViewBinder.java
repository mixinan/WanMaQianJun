// Generated code from Butter Knife. Do not modify!
package wyjinbu.AppTool.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AboutFragment$$ViewBinder<T extends wyjinbu.AppTool.fragment.AboutFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131034127, "field 'tvDaiMaJia'");
    target.tvDaiMaJia = finder.castView(view, 2131034127, "field 'tvDaiMaJia'");
    view = finder.findRequiredView(source, 2131034128, "field 'tvGank'");
    target.tvGank = finder.castView(view, 2131034128, "field 'tvGank'");
    view = finder.findRequiredView(source, 2131034129, "field 'tvSatan'");
    target.tvSatan = finder.castView(view, 2131034129, "field 'tvSatan'");
    view = finder.findRequiredView(source, 2131034125, "field 'tvVersion'");
    target.tvVersion = finder.castView(view, 2131034125, "field 'tvVersion'");
  }

  @Override public void unbind(T target) {
    target.tvDaiMaJia = null;
    target.tvGank = null;
    target.tvSatan = null;
    target.tvVersion = null;
  }
}
