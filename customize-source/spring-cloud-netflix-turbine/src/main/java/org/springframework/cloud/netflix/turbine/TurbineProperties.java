/*
 * Copyright 2013-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.netflix.turbine;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.StringUtils;

/**
 * @author Spencer Gibb
 * @author Gregor Zurowski
 */
@ConfigurationProperties("turbine")
public class TurbineProperties {

	private String clusterNameExpression;

	private String appConfig;

	private boolean combineHostPort = true;

	@Autowired
	private DiscoveryClient discoveryClient;

	/*public List<String> getAppConfigList() {
		if (!StringUtils.hasText(this.appConfig)) {
			return null;
		}
		String[] parts = StringUtils.commaDelimitedListToStringArray(this.appConfig);
		if (parts != null && parts.length > 0) {
			parts = StringUtils.trimArrayElements(parts);
			return Arrays.asList(parts);
		}
		return null;
	}*/

	public List<String> getAppConfigList(){
		List<String> discoverclient = discoveryClient.getServices();
		if(discoverclient.size() > 0){
			return discoverclient;
		}
		return null;
	}

	public String getClusterNameExpression() {
		return clusterNameExpression;
	}

	public void setClusterNameExpression(String clusterNameExpression) {
		this.clusterNameExpression = clusterNameExpression;
	}

	public String getAppConfig() {
		return appConfig;
	}

	public void setAppConfig(String appConfig) {
		this.appConfig = appConfig;
	}

	public boolean isCombineHostPort() {
		return combineHostPort;
	}

	public void setCombineHostPort(boolean combineHostPort) {
		this.combineHostPort = combineHostPort;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TurbineProperties that = (TurbineProperties) o;
		return Objects.equals(clusterNameExpression, that.clusterNameExpression) &&
				Objects.equals(appConfig, that.appConfig) &&
				Objects.equals(combineHostPort, that.combineHostPort);
	}

	@Override
	public int hashCode() {
		return Objects.hash(clusterNameExpression, appConfig, combineHostPort);
	}

	@Override
	public String toString() {
		return new StringBuilder("TurbineProperties{")
				.append("clusterNameExpression='").append(clusterNameExpression).append("', ")
				.append("appConfig='").append(appConfig).append("', ")
				.append("combineHostPort=").append(combineHostPort).append("}")
				.toString();
	}

}
