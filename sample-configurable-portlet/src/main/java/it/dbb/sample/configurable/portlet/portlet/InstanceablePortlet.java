package it.dbb.sample.configurable.portlet.portlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import it.dbb.sample.configurable.portlet.constants.InstanceablePortletKeys;
import it.dbb.sample.configurable.portlet.portlet.config.InstanceablePortletConfig;

/**
 * @author daniele
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true", "com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.preferences-unique-per-layout=false",
		"javax.portlet.display-name=sample-configurable-portlet", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + InstanceablePortletKeys.Instanceable, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, configurationPid = "it.dbb.sample.configurable.portlet.portlet.config.InstanceablePortletConfig", service = Portlet.class)
public class InstanceablePortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long groupId = themeDisplay.getScopeGroupId();

			config = ConfigurationProviderUtil.getGroupConfiguration(InstanceablePortletConfig.class, groupId);

			renderRequest.setAttribute("configurable", config);

		} catch (ConfigurationException ce) {
		}

		super.doView(renderRequest, renderResponse);
	}

	@Reference(service = ConfigurationProvider.class, unbind = "-")
	protected void setConfigurationProvider(ConfigurationProvider configurationProvider) {
		this.configurationProvider = configurationProvider;
	}

	protected ConfigurationProvider configurationProvider;

	protected InstanceablePortletConfig config;
}