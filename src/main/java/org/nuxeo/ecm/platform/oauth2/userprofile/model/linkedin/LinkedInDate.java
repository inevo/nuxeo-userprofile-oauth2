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
import java.util.GregorianCalendar;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * Auxiliary model for dates coming from LinkedIn API. Converts them in
 * java.util.Date.
 * 
 * @author <a href="mailto:tiago.cardoso@inevo.pt">Tiago Cardoso</a>
 * @since 5.7
 */

public class LinkedInDate extends GenericJson {
	@Key
	private int day;
	@Key
	private int month;
	@Key
	private int year;

	public Date getDate() {
		return new GregorianCalendar(year, month - 1, day).getTime();
	}
}
