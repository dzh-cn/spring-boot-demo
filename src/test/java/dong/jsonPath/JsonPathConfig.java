package dong.jsonPath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Member;
import java.util.Map;
import java.util.Set;

/**
 * json path config
 *
 * @author: dongzhihua
 * @time: 2018/5/31 16:09:05
 */
public class JsonPathConfig {

	private static Logger logger = LoggerFactory.getLogger(JsonPathConfig.class);

	private Map<String, Map<String, String>> config;

	private Set<String> basePackages;

	public JsonPathConfig(Map<String, Map<String, String>> config) {
		this.config = config;
		logger.debug("config: {}", config);
	}
	public JsonPathConfig(Map<String, Map<String, String>> config, Set<String> basePackages) {
		this.config = config;
		this.basePackages = basePackages;
		logger.debug("config: {}", config);
	}

	String getJsonPath(Member member) {
		String jsonPath = getJsonPath(member.getDeclaringClass().getName(), member.getName());
		if (jsonPath != null) {
			return jsonPath;
		}
		return member.getDeclaringClass().getSimpleName() + "." + member.getName();
	}

	String getJsonPath(String className, String name) {
		if (config == null) {
			return null;
		}
		Map<String, String> fieldMap = config.get(className);

		if (fieldMap == null) {
			return null;
		}
		String jsonPath = fieldMap.get(name);
		if (jsonPath == null) {
			return null;
		}
		return jsonPath;
	}

	public boolean content(Class clazz) {
		for (String basePackage : this.basePackages) {
			if (clazz.getName().startsWith(basePackage)) {
				return true;
			}
		}
		return false;
	}

	public void setBasePackages(Set<String> basePackages) {
		this.basePackages = basePackages;
		logger.debug("basePackages: {}", basePackages);
	}

	public Set<String> getBasePackages() {
		return basePackages;
	}
}
