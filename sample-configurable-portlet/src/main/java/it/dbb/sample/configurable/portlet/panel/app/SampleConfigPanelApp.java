package it.dbb.sample.configurable.portlet.panel.app;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.model.Portlet;

import it.dbb.sample.configurable.portlet.constants.InstanceablePortletKeys;

@Component(immediate = true, property = { "panel.app.order:Integer=300",
		"panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONFIGURATION }, service = PanelApp.class)
public class SampleConfigPanelApp extends BasePanelApp implements PanelApp {

	@Override
	public String getPortletId() {

		return InstanceablePortletKeys.InstanceableAdmin;
	}

	@Override
	@Reference(unbind = "-", target = "(javax.portlet.name=" + InstanceablePortletKeys.InstanceableAdmin + ")")
	public void setPortlet(Portlet portlet) {

		super.setPortlet(portlet);
	}
}
