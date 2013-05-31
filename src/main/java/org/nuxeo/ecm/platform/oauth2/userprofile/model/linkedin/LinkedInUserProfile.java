/*
 * (C) Copyright 2006-2013 Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     Tiago Cardoso <tiago.cardoso@inevo.pt> - initial API and implementation
 */

package org.nuxeo.ecm.platform.oauth2.userprofile.model.linkedin;

import java.util.Date;
import java.util.List;

import org.nuxeo.ecm.platform.oauth2.userprofile.model.AbstractUserProfile;
import org.nuxeo.ecm.platform.oauth2.userprofile.model.UserEducation;
import org.nuxeo.ecm.platform.oauth2.userprofile.model.UserExternal;
import org.nuxeo.ecm.platform.oauth2.userprofile.model.UserPosition;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * Implementation of LinkedIn API's Profile according to our interface.
 * 
 * @author <a href="mailto:tiago.cardoso@inevo.pt">Tiago Cardoso</a>
 * @since 5.7
 */

public class LinkedInUserProfile extends AbstractUserProfile {

	@Key
	private String firstName;

	@Key
	private String lastName;

	@Key
	private String pictureUrl;

	@Key
	private String id;

	@Key
	private String headline;

	@Key
	private String interests;

	@Key
	private String publicProfileUrl;

	@Key
	private LinkedInDate dateOfBirth;

	@Key
	private String emailAddress;

	@Key
	private Location location;

	@Key
	private String summary;

	@Key
	private String specialties;

	@Key
	private MemberUrlResources memberUrlResources;

	@Key
	private Educations educations;

	@Key
	private Positions positions;

	@Override
	public String getGivenName() {
		return firstName;
	}

	@Override
	public String getFamilyName() {
		return lastName;
	}

	public String getPublicProfileUrl() {
		return publicProfileUrl;
	}

	@Override
	public String getImageUrl() {
		return pictureUrl;
	}

	@Override
	public UserExternal getUrl() {
		LinkedInUserExternal external = new LinkedInUserExternal();
		external.setName("LinkedIn");
		external.setLink(publicProfileUrl);
		return external;
	}

	@Override
	public Date getBirthDate() {
		return dateOfBirth.getDate();
	}

	@Override
	public String getEmail() {
		return emailAddress;
	}

	@Override
	public String getHomeLocation() {
		return location.country.code;
	}

	@Override
	public UserPosition getWorksFor() {
		for (UserPosition position : getPositions()) {
			if (position.isCurrent()) {
				return position;
			}
		}
		return null;
	}

	@Override
	public UserEducation getAlumniOf() {
		for (UserEducation education : getEducations()) {
			if (education.isCurrent()) {
				return education;
			}
		}
		return null;
	}

	@Override
	public String getDescription() {
		return summary;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getHeadline() {
		return headline;
	}

	@Override
	public String getSpecialties() {
		return specialties;
	}

	@Override
	public String getInterests() {
		return interests;
	}

	@Override
	public List<? extends UserEducation> getEducations() {
		return educations.getValues();
	}

	@Override
	public List<? extends UserPosition> getPositions() {
		return positions.getValues();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<? extends UserExternal> getExternals() {
		List<LinkedInUserExternal> externals = (List<LinkedInUserExternal>) memberUrlResources
				.getValues();
		externals.add((LinkedInUserExternal) getUrl());
		return externals;
	}

	public static class Location extends GenericJson {
		@Key
		String name;

		@Key
		Country country;

		public static class Country extends GenericJson {
			@Key
			String code;
		}
	}

	public static class MemberUrlResources extends GenericJson {
		@Key("_total")
		int total;

		@Key
		List<LinkedInUserExternal> values;

		public List<? extends UserExternal> getValues() {
			return values;
		}

	}

	public static class Educations extends GenericJson {
		@Key("_total")
		int total;

		@Key
		List<LinkedInUserEducation> values;

		public List<? extends UserEducation> getValues() {
			return values;
		}

	}

	public static class Positions extends GenericJson {
		@Key("_total")
		int total;

		@Key
		List<LinkedInUserPosition> values;

		public List<? extends UserPosition> getValues() {
			return values;
		}

	}

}
