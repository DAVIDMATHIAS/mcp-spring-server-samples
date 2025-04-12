## Sample MCP Server Implementation, How to use it
<li>Step 1</li>
<p>Just run as spring application and add the following configuraiton into the .vscode/mcp.json </p> 
<code>
  {
    "servers": {
        "my-local-service": {
            "type": "sse",
            "url": "http://localhost:8081/sse"
        }
    }
}
</code>
