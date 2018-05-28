package fr.wildcodeschool.githubtracker.util;

import javax.faces.annotation.FacesConfig;

/**
 * Forces JSF 2.3 activation
 *
 * see https://github.com/javaee/glassfish/issues/22094
 */
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class JsfConfig {
    //nothing
}