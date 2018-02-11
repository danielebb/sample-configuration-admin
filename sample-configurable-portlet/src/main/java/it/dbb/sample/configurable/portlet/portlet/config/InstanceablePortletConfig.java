package it.dbb.sample.configurable.portlet.portlet.config;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(id = "it.dbb.sample.configurable.portlet.portlet.config.InstanceablePortletConfig")
public interface InstanceablePortletConfig {

	@Meta.AD(required = false)
	public String name();
}
