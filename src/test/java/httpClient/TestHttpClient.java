package httpClient;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.NumberUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * http client
 *
 * @author: dongzhihua
 * @time: 2018/5/31 12:16:01
 */
public class TestHttpClient {
	public static void main(String[] args) throws IOException {
		String url = "http://localhost/http/workflow/manage/myWorkflowTasks.data";
		long t;
		int count = 100;
		t = getSome(url, null, count);
		System.out.println(t);
		t = getSome(url, HttpClients.createMinimal(), count);
		System.out.println(":" + t);
	}

	static long getSome(String url, HttpClient httpClient, int count) throws IOException {
		List<Long> longs = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			long start = System.currentTimeMillis();
			get(httpClient, url);
			long st = System.currentTimeMillis() - start;
//			System.out.println(st);
			longs.add(st);
		}
		long s = 0;
		for (Long aLong : longs) {
			s += aLong;
		}
		return s / longs.size();
	}

	static String get(HttpClient httpClient, String url) throws IOException {
		boolean close = false;
		if(httpClient == null) {
			close = true;
			httpClient = HttpClients.createMinimal();
		}
		HttpGet get = new HttpGet(url);
		HttpResponse response = httpClient.execute(get);
		if (response.getStatusLine().getStatusCode() == 302) {
			return get(httpClient, response.getFirstHeader("Location").getValue());
		}
		HttpEntity entity = response.getEntity();
		String responseStr = EntityUtils.toString(entity);

		if(close) {
			HttpClientUtils.closeQuietly(httpClient);
		}
		HttpClientUtils.closeQuietly(response);
		return responseStr;
	}
}
