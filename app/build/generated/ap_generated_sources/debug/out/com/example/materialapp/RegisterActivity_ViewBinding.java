// Generated code from Butter Knife. Do not modify!
package com.example.materialapp;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegisterActivity_ViewBinding implements Unbinder {
  private RegisterActivity target;

  private View view7f08009b;

  private View view7f080052;

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisterActivity_ViewBinding(final RegisterActivity target, View source) {
    this.target = target;

    View view;
    target.temail = Utils.findRequiredViewAsType(source, R.id.text_name, "field 'temail'", TextInputLayout.class);
    target.tpassword = Utils.findRequiredViewAsType(source, R.id.text_password, "field 'tpassword'", TextInputLayout.class);
    view = Utils.findRequiredView(source, R.id.go_to_dahsboard, "method 'goToRegister'");
    view7f08009b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goToRegister();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_register, "method 'register'");
    view7f080052 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.register();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    RegisterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.temail = null;
    target.tpassword = null;

    view7f08009b.setOnClickListener(null);
    view7f08009b = null;
    view7f080052.setOnClickListener(null);
    view7f080052 = null;
  }
}
