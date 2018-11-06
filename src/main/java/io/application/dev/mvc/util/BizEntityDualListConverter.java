package io.application.dev.mvc.util;

import io.application.dev.mvc.model.LancamentoItem;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@FacesConverter(value="bizEntityDualListConverter")
public class BizEntityDualListConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        Object ret = null;
        if (arg1 instanceof PickList) {
            Object dualList = ((PickList) arg1).getValue();
            @SuppressWarnings("unchecked")
            DualListModel<LancamentoItem> dl = (DualListModel<LancamentoItem>) dualList;
            for (Object o : dl.getSource()) {
                String id = "" + ((LancamentoItem) o).getId();
                if (arg2.equals(id)) {
                    ret = o;
                    break;
                }
            }
            for (Object o : dl.getTarget()) {
                String id = "" + ((LancamentoItem) o).getId();
                if (arg2.equals(id)) {
                    ret = o;
                    break;
                }
            }
            if (ret == null)
                for (Object o : dl.getTarget()) {
                    String id = "" + ((LancamentoItem) o).getId();
                    if (arg2.equals(id)) {
                        ret = o;
                        break;
                    }
                }
        }
        return ret;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        String str = "";
        if (arg2 instanceof LancamentoItem) {
            str = "" + ((LancamentoItem) arg2).getId();
        }
        return str;
    }
}