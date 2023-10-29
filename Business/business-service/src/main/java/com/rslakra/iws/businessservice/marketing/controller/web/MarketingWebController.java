package com.rslakra.iws.businessservice.marketing.controller.web;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.spring.controller.web.AbstractWebController;
import com.devamatre.framework.spring.filter.Filter;
import com.devamatre.framework.spring.parser.Parser;
import com.rslakra.iws.businessservice.marketing.persistence.entity.Marketing;
import com.rslakra.iws.businessservice.marketing.service.MarketingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @author: Rohtash Lakra (rlakra)
 * @since: 9/30/19 5:38 PM
 */
@Controller
@RequestMapping("/marketing")
public class MarketingWebController extends AbstractWebController<Marketing, Long> {

    // marketingService
    private final MarketingService marketingService;

    /**
     * @param marketingService
     */
    @Autowired
    public MarketingWebController(MarketingService marketingService) {
        this.marketingService = marketingService;
    }

    /**
     * Saves the <code>t</code> object.
     *
     * @param marketing
     * @return
     */
    @PostMapping("/save")
    @Override
    public String save(Marketing marketing) {
        if (BeanUtils.isNotNull(marketing.getId())) {
            Marketing oldMarketing = marketingService.getById(marketing.getId());
            BeanUtils.copyProperties(marketing, oldMarketing);
            marketing = marketingService.update(oldMarketing);
        } else {
            marketing = marketingService.create(marketing);
        }

        return "redirect:/marketing/list";
    }

    /**
     * Returns the list of <code>T</code> objects.
     *
     * @param model
     * @return
     */
    @GetMapping("/list")
    @Override
    public String getAll(Model model) {
        List<Marketing> marketings = marketingService.getAll();
        model.addAttribute("marketings", marketings);
        return "views/marketing/listMarketings";
    }

    /**
     * Filters the list of <code>T</code> objects.
     *
     * @param model
     * @param filter
     * @return
     */
    @Override
    public String filter(Model model, Filter filter) {
        return null;
    }

    /**
     * @param model
     * @param allParams
     * @return
     */
    @Override
    public String filter(Model model, Map<String, Object> allParams) {
        return null;
    }

    /**
     * Create the new object or Updates the object with <code>id</code>.
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(path = {"/create", "/update/{id}"})
    @Override
    public String editObject(Model model, @PathVariable(name = "id") Long id) {
        Marketing marketing = null;
        if (BeanUtils.isNotNull(id)) {
            marketing = marketingService.getById(id);
        } else {
            marketing = new Marketing();
        }
        model.addAttribute("marketing", marketing);

        return "views/marketing/editMarketing";
    }


    /**
     * Deletes the object with <code>id</code>.
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    @Override
    public String delete(Model model, @PathVariable(name = "id") Long id) {
        marketingService.delete(id);
        return "redirect:/marketing/list";
    }

    /**
     * @return
     */
    @Override
    public Parser<Marketing> getParser() {
        return null;
    }
}
