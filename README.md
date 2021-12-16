# CitySite

Welcome to CitySites! 

CitySites is for those looking for more to do in the great city of San Antonio and soon, a city near you! CitySites is a Full-Stack Web application that utilizes JavaScript, HTML, CSS, and Java with SpringBoot framework. Powered by Google APIs and the great minds of the CitySites development team, you, the user, will be able to find fun activities, save your favorite places, and even add your own activities. CitySites will show you where to go!

# To Do's to Properly Run Application

- Clone project
- Go to the DatabaseSeeder and UNCOMMENT all of the code
- Go to the example.properties and copy the contents
- Create a file titled application.properties in src/main/resources
- Paste the contents from example.properties into application.properties
  - Update spring.datasource.username and spring.datasource.password with your correct credentials
- You will need to obtain keys for Mapbox API, OpenWeather API, and Google APIs.
  - Google API Key Instructions:
    - Go to: https://developers.google.com/maps
    - Click “Get Started”
    - Sign into your Google account or create one to access Google Maps APIs
    - Once signed in, you will see the Google Maps Platform Overview tab
    - Go to the credentials tab on the left
    - At the top of the Credentials page, click “ + CREATE CREDENTIALS” and “API key”
    - Your key will be generated, keep the setting to the default to ensure your key is “unrestricted”
      - If you wish to restrict your key to your IP address, follow the onscreen instructions
    - You are now ready to use your keys.
  - OpenWeather API Instructions:
    - Go to: https://openweathermap.org/
    - Sign in or create a free account
    - Once signed in, head to Account and click “API keys”
    - On the left, give the key a name and click the button to “Generate”
    - You are now able to use your key.
  - MapBox API(s) Instructions:
    - Go to: https://www.mapbox.com/
    - Sign in or create a free account
    - Once signed in, click “Tokens” at the top
    - Click the button “+ Create a token”
    - Name the token, leave all default boxes checked
    - You now have access and can use your mapbox API key.
- Paste the Mapbox key and the OpenWeather API key into the appropraite field in the application.properties file
- Navigate to the home.html and the add-activity.html
  - At the very bottom of both files, replace {YOUR_KEY_HERE} in the script tag below the TEST KEY comment with the key you obtained from the Google API(s) Instructions
    - Comment in the TEST KEY
    - Make sure DEPLOY KEY and MAIN KEY script tags above are commented out
- Run the project

Enjoy!
