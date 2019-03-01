/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.usermaint;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author dgrfiv
 */
@Named(value = "mainNavigation")
@SessionScoped
public class MainNavigation implements Serializable {

    /**
     * Creates a new instance of MainNavigation
     */
    public MainNavigation() {
    }

    public String mainNavLanding() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm;
        fm = new FacesMessage("Home", "You are in landing page now!");
        context.addMessage(null, fm);
        return "landing?faces-redirect=true";
    }

    public String mainNavAbout() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm;
        fm = new FacesMessage("About Us", "You are in about page now!");
        context.addMessage(null, fm);
        return "about?faces-redirect=true";
    }

    public String mainNavService() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm;
        fm = new FacesMessage("Services", "You are in service page now!");
        context.addMessage(null, fm);
        return "service?faces-redirect=true";
    }

    public String mainNavProducts() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm;
        fm = new FacesMessage("Products", "You are in products page now!");
        context.addMessage(null, fm);
        return "products?faces-redirect=true";
    }

    public String mainNavPortfolio() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm;
        fm = new FacesMessage("Portfolio", "You are in portfolio page now!");
        context.addMessage(null, fm);
        return "portfolio?faces-redirect=true";
    }

    public String mainNavContact() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm;
        fm = new FacesMessage("Contact", "You are in contact page now!");
        context.addMessage(null, fm);
        return "contact?faces-redirect=true";
    }
}
