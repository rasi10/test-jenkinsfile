*** Settings ***
Library                              HttpLibrary.HTTP
Library                              Collections
Library                              String
Library                              json

*** variables ***
${http_context}=                     localhost:8080
${http_variable}=                    http
${header_content_json}               application/json
${header_accept_all}                 */*         
#GET endpoints
${get_all_clients_endpoint}=         /hotel-rest/webresources/client/
${get_clients_counter_endpoint}=     /hotel-rest/webresources/client/count
#POST endpoints
${create_a_client_endpoint}=         /hotel-rest/webresources/client/
#PUT endpoints
${edit_a_client_endpoint}=           /hotel-rest/webresources/client/   #the id should be included
#Delete endpoint
${delete_a_client_endpoint}=         /hotel-rest/webresources/client/   #the id should be included

*** keywords ***
Generate a random client
    ${id}=                           Set Variable                   100
    ${name}=                         Generate random String         10                  [LOWER]
    ${createDate}=                   Set Variable                   1473633063279
    ${email}=                        Catenate                       SEPARATOR=          ${name}        @email.com
    ${socialSecurityNumber}=         Generate Random String         9                   [NUMBERS]
    ${gender}=                       Generate Random String         1                   MF
    ${dictionary}=                   Create Dictionary  id=${id}    name=${name}  createDate=${createDate}   email=${email}   gender=${gender}   socialSecurityNumber=${socialSecurityNumber}
    ${client_json}=                  Stringify Json                 ${dictionary}
    [return]                         ${client_json}
    

## Method using the GET   /webresources/client/count
Get the total of clients                      
    Create Http Context              ${http_context}     ${http_variable}     
    Set Request Header               Content-Type        ${header_content_json}
    Set Request Header               Accept              ${header_accept_all}
    HttpLibrary.HTTP.GET             ${get_clients_counter_endpoint}    
    ${response_status}=              Get response Status
    ${total_clients}=                Get response body      
    log to console                   ${\n}Getting the total of clients:${total_clients}
    log to console                   ${\n}Status code:${response_status}   
    Should contain                   ${response_status}	                      200 
    #log to console                   ${total_clients}
    [Return]                         ${total_clients}             

## Method using  POST  /webresources/client/count
Create a client    
    ${request_body} =                Generate a random client
    Create Http Context              ${http_context}     ${http_variable}    
    Set Request Header               Content-Type        ${header_content_json}
    Set Request Header               Accept              ${header_accept_all}        
    Set request body                 ${request_body}        
    Log to console                   ${\n}Creating a new client${\n}DATA:${request_body}    
    HttpLibrary.HTTP.POST            ${create_a_client_endpoint}
    ${response_status}=              Get response Status    
    log to console                   ${\n}Status code:${response_status}  
    Should contain                   ${response_status}	                      204  

        