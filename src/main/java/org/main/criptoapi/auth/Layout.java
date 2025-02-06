package org.main.criptoapi.auth;

import lombok.Data;
import org.springframework.web.servlet.ModelAndView;

@Data
public class Layout {
    private ModelAndView modelAndView;

    public Layout() {
    }

}
