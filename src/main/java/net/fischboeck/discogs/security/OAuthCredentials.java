//
//  Copyright 2016 M. Fischboeck 
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
//

package net.fischboeck.discogs.security;


public class OAuthCredentials {

	 String tempToken;
	 String verifierCode;
	
	 
	 String authorizationUrl;
	 String accessToken;
	 String sharedSecret;
	
	 OAuthCredentials() {
		 
	 }
	 
	 public OAuthCredentials(String accessToken, String sharedSecret) {
		 this.accessToken = accessToken;
		 this.sharedSecret = sharedSecret;
	 }

	 public String getAuthorizationUrl() {
		 return this.authorizationUrl;
	 }
	 
	 public String getAccessToken() {
		 return this.accessToken;
	 }
	 
	 public String getSharedSecret() {
		 return this.sharedSecret;
	 }
}
