package com.volkruss.BookOhFoo.sell.usecase;

import com.volkruss.BookOhFoo.sell.controller.RegisterForm;
import com.volkruss.BookOhFoo.sell.usecase.output.RegistViewOutput;

public interface SellRegisterService {
    RegistViewOutput action(RegisterForm registerForm);
}
