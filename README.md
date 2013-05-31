nuxeo-userprofile-oauth2
========================

This plugin retrives user profile information from several Social Sites using Oauth2 API.

By now only LinkedIn is implemented.

Future work:
------------

- Better integration with Schema.org specs on the model side.
- Integrate Facebook and Google+

Example usage on WebEngine:
---------------------------

	@GET
	@Path("import")
	public Object doImportProfile(@QueryParam("profileProvider") String provider)
			throws ClientException, Exception {
		HttpServletRequest request = getContext().getRequest();
		Oauth2ProfileProviderService oauth2ProfileService = Framework
				.getService(Oauth2ProfileProviderService.class);

		UserProfile userProfile = oauth2ProfileService.getUserProfile(
				provider, request);
		if (userProfile == null) {

			// set the redirect uri (to this same action)
			StringBuffer url = request.getRequestURL();
			String queryString = request.getQueryString();
			if (queryString != null) {
				url.append('?');
				url.append(queryString);
			}
			String redirectUri = url.toString();
			request.getSession().setAttribute(
					UserProfileConstants.REDIRECT_URI_SESSION_ATTRIBUTE,
					redirectUri);

			// get authentication url
			String authenticationUrl = oauth2ProfileService
					.getAuthenticationUrl(provider, request);
			throw new WebTemporaryRedirectException(authenticationUrl);
		}
		
		// We have the user profile from our choosen provider. Lets update our Nuxeo user profile
		updateNuxeoUserProfile(userProfile);
		return redirect(getPath() + "/showProfile");
	}

