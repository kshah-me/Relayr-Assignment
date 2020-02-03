*** Settings ***
Documentation    Suite description
Library         RequestsLibrary
Library         Collections

*** Variables ***
${base_url}     http://restapi.demoqa.com
${auth_id}  ToolsQA
${auth_Pass}    TestPassword

*** Test Cases ***
Basic_Auth
    ${auth}=    create list  ${auth_id}    ${auth_Pass}
    create session  myssion     ${base_url}     auth=${auth}
    ${response}=    get request  myssion    /authentication/CheckForAuthentication/
    log to console  ${response.content}
