// Generated code from Butter Knife. Do not modify!
package com.example.materialapp;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Dashboard_ViewBinding implements Unbinder {
  private Dashboard target;

  private View view7f08009a;

  private View view7f08009c;

  @UiThread
  public Dashboard_ViewBinding(Dashboard target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Dashboard_ViewBinding(final Dashboard target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.go_to_add, "method 'goToAdd'");
    view7f08009a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goToAdd();
      }
    });
    view = Utils.findRequiredView(source, R.id.go_to_delete, "method 'goToDelete'");
    view7f08009c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goToDelete();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view7f08009a.setOnClickListener(null);
    view7f08009a = null;
    view7f08009c.setOnClickListener(null);
    view7f08009c = null;
  }
}
