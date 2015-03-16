myCust - AppDirect Integration Challenge
=======================================

Requirements :
-----------

* JAVA 7
* MySQL
* Maven

Application configuration
-------------------------

Under src/main/resources folder : 

* application.properties
  * oAuthConsumerKey
  * oAuthConsumerSecret
  
* database.properties
  * db.url
  * db.username
  * db.password

Installation
------------

maven clean package

AppDirect Configuration
-----------------------

Single Sign On
--------------

**Login URL**
http://[HOST]/[CONTEXT]/login-openid?openid_identifier={openid}

**OpenID Realm**
http://[HOST]/[CONTEXT]/login-openid*

Subscriptions
-------------

**Subscription Create Notification URL**
http://[HOST]/[CONTEXT]/api/v1/appDirect/subscription/order?token={eventUrl}

**Subscription Change Notification URL**
http://[HOST]/[CONTEXT]/api/v1/appDirect/subscription/change?token={eventUrl}

**Subscription Cancel Notification URL**
http://[HOST]/[CONTEXT]/api/v1/appDirect/subscription/cancel?token={eventUrl}

**Subscription Status Notification URL**
http://[HOST]/[CONTEXT]/api/v1/appDirect/subscription/change?token={eventUrl}

Access Management
-----------------

**User Assignment Notification URL**
http://[HOST]/[CONTEXT]/api/v1/appDirect/user/assign?token={eventUrl}

**User Unassignment Notification URL**
http://[HOST]/[CONTEXT]/api/v1/appDirect/user/unassign?token={eventUrl}

