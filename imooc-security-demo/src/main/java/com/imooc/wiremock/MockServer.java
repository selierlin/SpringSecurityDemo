package com.imooc.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.removeAllMappings;

/**
 * 向WireMock服务里注册Rest服务
 */
public class MockServer {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		configureFor(9600);
		removeAllMappings();

		//get请求
		WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/user/1"))
				.willReturn(WireMock.aResponse()
						//body里面写 json
						.withBody("{\"username\":FantJ}")
						//返回状态码
						.withStatus(200)));
	}

}