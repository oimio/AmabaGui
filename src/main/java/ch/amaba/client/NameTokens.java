/**
 * Copyright 2011 Amaba.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package ch.amaba.client;

/**
 * 
 * @author ROG
 */
public class NameTokens {
	public static final String aboutUsPage = "!aboutUsPage";

	public static final String contactPage = "!contactPage";

	public static final String homePage = "!homePage";

	public static final String rechercheDetaillee = "!rechercheDetaillee";

	public static final String modifierDonnees = "!modifierDonnees";

	public static final String confidentialite = "!confidentialite";

	public static final String mesPhotos = "!mesPhotos";

	public static final String hitList = "!hitList";

	public static final String login = "!login";

	public static final String messages = "!messages";

	public static String getLoginPage() {
		return NameTokens.login;
	}

	public static String getAboutUsePage() {
		return NameTokens.aboutUsPage;
	}

	public static String getContactPage() {
		return NameTokens.contactPage;
	}

	public static String getHomePage() {
		return NameTokens.homePage;
	}

	public static String getHitList() {
		return NameTokens.hitList;
	}
}