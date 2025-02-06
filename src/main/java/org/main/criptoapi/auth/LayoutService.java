package org.main.criptoapi.auth;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@Service
public class LayoutService {


    public ModelAndView getPage(String page)  {
        ModelAndView mav = new ModelAndView("layout");
        mav.addObject("page", page);
        return mav;
    }

    public Layout getLayout(String page){
        ModelAndView mav = new ModelAndView("layout");
        mav.addObject("page", page);
        Layout layout = new Layout();
        layout.setModelAndView(mav);
        return layout;
    }

    public Layout getLayout(Model model, String page){
        Layout layout = getLayout(page);
        ModelAndView mav = layout.getModelAndView();
        if(model.containsAttribute("swal")) {
            mav.addObject("swal", model.getAttribute("swal"));
        }
        return layout;
    }
}
