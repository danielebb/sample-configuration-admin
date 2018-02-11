package it.dbb.sample.configurable.portlet.configuration.provider;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;

import it.dbb.sample.configurable.portlet.portlet.config.InstanceablePortletConfig;

@Component
public class InstanceablePortletConfigurationBeanDeclaration implements ConfigurationBeanDeclaration {

	@Override
	public Class<?> getConfigurationBeanClass() {
		
		return InstanceablePortletConfig.class;
	}

}
