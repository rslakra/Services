package com.rslakra.iws.taskservice.account.controller.web;

import com.rslakra.frameworks.spring.controller.web.AbstractWebController;
import com.rslakra.frameworks.spring.filter.Filter;
import com.rslakra.iws.taskservice.account.persistence.entity.Role;
import com.rslakra.iws.taskservice.account.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

/**
 * @author: Rohtash Lakra (rlakra)
 * @since: 9/30/19 5:38 PM
 */
@Controller
@RequestMapping("/roles")
public class RoleWebController extends AbstractWebController<Role> {

    // roleService
    private final RoleService roleService;

    /**
     * @param roleService
     */
    @Autowired
    public RoleWebController(RoleService roleService) {
        this.roleService = roleService;
    }


    @Override
    public void validate(Optional<Long> id) {
        super.validate(id);
    }

    /**
     * Saves the <code>t</code> object.
     *
     * @param role
     * @return
     */
    @PostMapping("/save")
    @Override
    public String save(Role role) {
        role = roleService.update(role);
        return "redirect:/roles/list";
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
        List<Role> roles = roleService.getAll();
        model.addAttribute("roles", roles);
        return "roles/listRoles";
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
     * Create the new object or Updates the object with <code>id</code>.
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(path = {"/create", "/update/{id}"})
    @Override
    public String editObject(Model model, @PathVariable(name = "id") Optional<Long> id) {
        Role role = null;
        if (id.isPresent()) {
            role = roleService.getById(id.get());
        } else {
            role = new Role();
        }
        model.addAttribute("role", role);

        return "roles/editRole";
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
    public String delete(Model model, @PathVariable(name = "id") Optional<Long> id) {
        validate(id);
        roleService.delete(id.get());
        return "redirect:/roles/list";
    }

}
