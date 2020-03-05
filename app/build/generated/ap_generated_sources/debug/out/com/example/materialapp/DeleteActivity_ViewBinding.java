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

public class DeleteActivity_ViewBinding implements Unbinder {
  private DeleteActivity target;

  private View view7f08009b;

  private View view7f08007b;

  @UiThread
  public DeleteActivity_ViewBinding(DeleteActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DeleteActivity_ViewBinding(final DeleteActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.go_to_dahsboard, "method 'goToDashbard'");
    view7f08009b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goToDashbard();
      }
    });
    view = Utils.findRequiredView(source, R.id.delete_btn, "method 'delete'");
    view7f08007b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.delete();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view7f08009b.setOnClickListener(null);
    view7f08009b = null;
    view7f08007b.setOnClickListener(null);
    view7f08007b = null;
  }
}
