
package com.benchmark.jersey;

import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class NoCacheResponseFilter implements ContainerResponseFilter {
    @Override
    public void filter(jakarta.ws.rs.container.ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        responseContext.getHeaders().add("Pragma", "no-cache");
    }
}
