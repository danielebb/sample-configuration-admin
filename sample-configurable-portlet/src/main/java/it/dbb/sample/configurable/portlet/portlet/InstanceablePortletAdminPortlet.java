package it.dbb.sample.configurable.portlet.portlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.ValidatorException;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.settings.ModifiableSettings;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.SettingsException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import it.dbb.sample.configurable.portlet.constants.InstanceablePortletKeys;
import it.dbb.sample.configurable.portlet.portlet.config.InstanceablePortletConfig;
import it.dbb.sample.configurable.portlet.util.SettingsUtil;

@Component(service = Portlet.class, property = { "com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.preferences-unique-per-layout=false",
		"javax.portlet.display-name=sample-configurable-admin-portlet",
		"javax.portlet.init-param.template-path=/", "javax.portlet.init-param.view-template=/admin/view.jsp",
		"javax.portlet.name=" + InstanceablePortletKeys.InstanceableAdmin,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user", }, configurationPid = "it.dbb.sample.configurable.portlet.portlet.config.InstanceablePortletConfig")
public class InstanceablePortletAdminPortlet extends InstanceablePortlet {

	public void editSettings(ActionRequest actionRequest, ActionResponse actionResponse)
			throws SettingsException, ValidatorException, IOException {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long groupId = themeDisplay.getScopeGroupId();

		Settings settings = SettingsUtil.getGroupSettings(InstanceablePortletConfig.class.getName(), groupId);
		ModifiableSettings modifiableSettings = settings.getModifiableSettings();

		String name = ParamUtil.getString(actionRequest, "name");

		modifiableSettings.setValue("name", name);

		modifiableSettings.store();
	}
}
