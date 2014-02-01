
<html>
    <head>
         <title>Visit List</title>
         <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}"></link>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link action="index">Home</g:link></span>
            <span class="menuButton"><g:link action="create">New Visit</g:link></span>
        </div>
        <div class="body">
           <h1>Visit List</h1>
            <g:if test="flash['message']">
                 <div class="message">
                       ${flash['message']}
                 </div>
            </g:if>
           <table>
               <tr>
                   
                                      
                        <th>Id</th>
                                      
                        <th>Entry</th>
                                      
                        <th>User</th>
                   
                   <th></th>
               </tr>
               <g:each in="${visitList}">
                    <tr>
                       
                            <td>${it.id}</td>
                       
                            <td>${it.entry}</td>
                       
                            <td>${it.user}</td>
                       
                       <td class="actionButtons">
                            <span class="actionButton"><g:link action="show" id="${it.id}">Show</g:link></span>
                       </td>
                    </tr>
               </g:each>
           </table>
        </div>
    </body>
</body>
            