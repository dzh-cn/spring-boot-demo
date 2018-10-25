package dong.jsonPath;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * json path
 * 将jsonPath对应的set方法配置到jsonPathConfig中，实现从json中解析对象，只包含需要的属性
 *
 * @author: dongzhihua
 * @time: 2018/5/31 14:30:48
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dong/jsonPath/applicationContext.xml")
public class TestJsonPath {
	@Autowired
	JsonPathConfig jsonPathConfig;
	@Test
	public void main() throws IOException {
		Company company = new Company();
		parse(company, new File("D:\\02征信认证平台\\06系统接口\\企业征信\\四期\\工商基本信息\\有数\\simple.json"));
		System.out.println(company.getShareHolderList().get(0));
	}

	<T> T parse(T bean, Object object) throws IOException {
		ReadContext context = JsonPath.parse(object);
		return parse(bean, context);
	}

	<T> T parse(T bean, ReadContext readContext) {

		Method[] declaredMethods = bean.getClass().getDeclaredMethods();
		for (Method declaredMethod : declaredMethods) {
			if (declaredMethod.getName().startsWith("set") && declaredMethod.getParameterCount() == 1) {
				String jsonPath = jsonPathConfig.getJsonPath(declaredMethod);
				Class clazz = declaredMethod.getParameterTypes()[0];

				Object value;
				if (jsonPathConfig.content(clazz)) {
					value = parse(bean, readContext.read(jsonPath));
				} else {
					value = readContext.read(jsonPath, clazz);
				}
				try {
					declaredMethod.invoke(bean, value);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}
		return bean;
	}

	static class Company {
		private String[] holderNames;
		private List<ShareHolder> shareHolderList;

		public void setHolderNames(String[] holderNames) {
			this.holderNames = holderNames;
		}
		public String[] getHolderNames() {
			return holderNames;
		}

		public List<ShareHolder> getShareHolderList() {
			return shareHolderList;
		}

		public void setShareHolderList(List<ShareHolder> shareHolderList) {
			this.shareHolderList = shareHolderList;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("{");
			sb.append("\"holderNames\":")
					.append(Arrays.toString(holderNames));
			sb.append(",\"shareHolderList\":")
					.append(shareHolderList);
			sb.append('}');
			return sb.toString();
		}
	}
	static class ShareHolder{
		private String shareholderName;

		public String getShareholderName() {
			return shareholderName;
		}

		public void setShareholderName(String shareholderName) {
			this.shareholderName = shareholderName;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("{");
			sb.append("\"shareholderName\":\"")
					.append(shareholderName).append('\"');
			sb.append('}');
			return sb.toString();
		}
	}
}
