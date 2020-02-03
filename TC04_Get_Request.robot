*** Settings ***
Documentation    Suite description
Library      RequestsLibrary
Library      os
Library      Collections
Library      JSONLibrary

*** Variables ***
${base_url}=    https://restcountries.eu

*** Test Cases ***
Get_CountryInfo
    create session  mysession   ${base_url}
    ${response}=    get request  mysession  /rest/v2/alpha/IN
    #${status_code}=  convert to string    ${response.status_code}
    #should be equal  ${status_code}     200

    ${json_object}=  to json   ${response.content}
    #log to console  ${json_object}

    ${country_name}=    get value from json     ${json_object}      $.name
    log to console      ${country_name}

    ${curremcy_code}=    get value from json     ${json_object}      $.currencies[0].code
    log to console      ${curremcy_code}