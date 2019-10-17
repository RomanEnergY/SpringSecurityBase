package com.zverikRS.config.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * После того как мы унаследуем объект  AbstractSecurityWebApplicationInitializer
 * говорим, в приложении используем spring-security, далее требуется его настроить
 */
public class SecurityInit extends AbstractSecurityWebApplicationInitializer {
}
