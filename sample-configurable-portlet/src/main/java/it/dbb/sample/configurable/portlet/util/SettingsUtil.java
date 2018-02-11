package it.dbb.sample.configurable.portlet.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.settings.CompanyServiceSettingsLocator;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.settings.PortletInstanceSettingsLocator;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.SettingsException;
import com.liferay.portal.kernel.settings.SettingsFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;

public class SettingsUtil {

	public static Settings getSettings(String settingsScope, String serviceName, ThemeDisplay themeDisplay)
			throws PortalException {

		if (settingsScope.equals("company")) {

			return getCompanySettings(serviceName, themeDisplay.getCompanyId());

		} else if (settingsScope.equals("group")) {

			return getGroupSettings(serviceName, themeDisplay.getSiteGroupId());

		} else if (settingsScope.equals("portletInstance")) {

			return getPortletInstanceSettings(serviceName, themeDisplay.getLayout());
		}

		throw new IllegalArgumentException("Invalid settings scope " + settingsScope);
	}

	public static Settings getPortletInstanceSettings(String portletInstanceKey, Layout layout) throws SettingsException {
		
		return SettingsFactoryUtil.getSettings(new PortletInstanceSettingsLocator(layout, portletInstanceKey));
	}

	public static Settings getGroupSettings(String serviceName, long groupId) throws SettingsException {

		return SettingsFactoryUtil.getSettings(new GroupServiceSettingsLocator(groupId, serviceName));
	}

	public static Settings getCompanySettings(String serviceName, long companyId) throws SettingsException {

		return SettingsFactoryUtil.getSettings(new CompanyServiceSettingsLocator(companyId, serviceName));
	}
}
