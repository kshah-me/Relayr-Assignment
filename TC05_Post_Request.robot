*** Settings ***
Documentation    Suite description
Library      RequestsLibrary
Library      os
Library      Collections
Library      JSONLibrary

*** Variables ***

${base_url}=    http://restapi.demoqa.com/customer

*** Test Cases ***
Post_Customer_Creation_Request
    create session  mysession  ${base_url}
    ${body}=    create dictionary  FirstName=Krishna    LastName=Shah   UserName=kshah  Password=kshah1234  Email=shahkrishna@gmail.com
    ${header}=  create dictionary  Content-Type=application/json
    ${response}=    post request    mysession   /register   data=${body}    headers=${header}

    ${status_code}=  convert to string   ${response.status_code}
    should be equal  ${status_code}     200

    ${res_body}=  convert to string  ${response.content}
    should contain  ${res_body}     OPERATION_SUCCESS
    should contain  ${res_body}     Operation completed successfully