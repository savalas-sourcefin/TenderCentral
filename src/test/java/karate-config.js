function fn() {
  var env = karate.env || 'dev';

  var config = {
  // Base URLs
    baseUrl: 'https://app-sf-tenderhub-dev.azurewebsites.net',
//    authUrl: 'https://app-sf-tenderhub-dev.azurewebsites.net/auth',

  // API Endpoints
    endpoints: {
      health: '/health',
      /*login: '/api/auth/login',
      users: '/api/users',
      tenders: '/api/tenders',*/
      provinces: '/constants/provinces',
      departments: '/constants/departments',
      categories: '/constants/categories'
    },



  };

  if (env === 'qa') {
    config.baseUrl = '';
  }

  // Optional: fetch token dynamically
  // var loginResponse = karate.callSingle('classpath:tenderhub/auth/login.feature', config);
  // config.token = loginResponse.token;

  return config;
}

