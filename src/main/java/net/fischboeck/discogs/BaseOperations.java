package net.fischboeck.discogs;

import java.io.BufferedInputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class BaseOperations {

	protected static final String DEFAULT_BASE_URL = "https://api.discogs.com";
	
	protected CloseableHttpClient httpClient;
	protected ObjectMapper mapper;
	
	protected boolean isAuthenticatedClient;
	
	private String authenticationToken;

	
	BaseOperations(CloseableHttpClient client, ObjectMapper mapper) {
		this.httpClient = client;
		this.mapper = mapper;
	}
	

	protected StringBuilder createPageParameters(StringBuilder urlBuilder, PageRequest page) {
		if (page != null) {
			urlBuilder.append("?page=");
			
			if (page.getPage() > PageRequest.DEFAULT_PAGE) {
				urlBuilder.append(page.getPage());
			} else {
				urlBuilder.append(PageRequest.DEFAULT_PAGE);
			}
			
			if (page.getSize() > 0) {
				urlBuilder.append(page.getSize());
			} else {
				urlBuilder.append(PageRequest.DEFAULT_PAGE_SIZE);
			}
		}
		return urlBuilder;
	}
	
	protected <T> T doRequest(String url, Class<T> type) throws ClientException {
		
		CloseableHttpResponse response = null;
		
		try {
			response = doHttpRequest(url);
			HttpEntity entity = response.getEntity();
		
			BufferedInputStream in2 = new BufferedInputStream(entity.getContent());
			
			T retval = mapper.readValue(in2, type);
			return retval;
		} catch (EntityNotFoundException ex) {
			return null;
		} catch (JsonMappingException jme) {
			throw new ClientException(jme.getMessage());
		} catch (JsonParseException jpe) {
			throw new ClientException(jpe.getMessage());
		} catch (IOException ioe) {
			throw new ClientException(ioe.getMessage());
		} finally {
			closeSafe(response);
		}
	}
	
	
	protected <T> T doRequest(String url, JavaType type) throws ClientException {
		
		CloseableHttpResponse response = null;
		
		try {
			response = doHttpRequest(url);
			HttpEntity entity = response.getEntity();
			
			BufferedInputStream in2 = new BufferedInputStream(entity.getContent());
			
			T retval = mapper.readValue(in2, type);
			return retval;
		} catch (EntityNotFoundException ex) {
			return null;
		} catch (JsonMappingException jme) {
			throw new ClientException(jme.getMessage());
		} catch (JsonParseException jpe) {
			throw new ClientException(jpe.getMessage());
		} catch (IOException ioe) {
			throw new ClientException(ioe.getMessage());
		} finally {
			closeSafe(response);
		}
	}
	
	
	protected CloseableHttpResponse doHttpRequest(String url) throws EntityNotFoundException, ClientException {
		
		HttpGet request = new HttpGet(url);
		request.addHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
		
		if (this.authenticationToken != null && !this.authenticationToken.isEmpty()) {
			request.addHeader(HttpHeaders.AUTHORIZATION,
					"Discogs token=" + this.authenticationToken);
		}
		
		CloseableHttpResponse response = null;
		
		try {
			response = this.httpClient.execute(request);
			
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
				response.close();
				throw new EntityNotFoundException("API returned 404 on request GET " + url);
			}
			
			return response;
		
		} catch (ClientProtocolException cpe) {
			throw new ClientException(cpe.getMessage());
		} catch (IOException ioe) {
			throw new ClientException(ioe.getMessage());
		}
	}

	
	private void closeSafe(CloseableHttpResponse response) {
		if (response != null) {
			try {
				response.close();
			} catch (IOException ex) {
				// noop
			}
		}
	}
}
