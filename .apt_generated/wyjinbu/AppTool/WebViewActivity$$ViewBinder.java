// Generated code from Butter Knife. Do not modify!
package wyjinbu.AppTool;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class WebViewActivity$$ViewBinder<T extends wyjinbu.AppTool.WebViewActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131034120, "field 'tvTitle'");
    target.tvTitle = finder.castView(view, 2131034120, "field 'tvTitle'");
    view = finder.findRequiredView(source, 2131034121, "field 'mProgressBar'");
    target.mProgressBar = finder.castView(view, 2131034121, "field 'mProgressBar'");
    view = finder.findRequiredView(source, 2131034119, "field 'ibChrome'");
    target.ibChrome = finder.castView(view, 2131034119, "field 'ibChrome'");
    view = finder.findRequiredView(source, 2131034122, "field 'mWebView'");
    target.mWebView = finder.castView(view, 2131034122, "field 'mWebView'");
  }

  @Override public void unbind(T target) {
    target.tvTitle = null;
    target.mProgressBar = null;
    target.ibChrome = null;
    target.mWebView = null;
  }
}
