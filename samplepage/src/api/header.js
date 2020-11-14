export default function makeheader() {
    return {
      headers: {
        "Content-type": "application/json",
        "session_id":document.cookie.match("JSESSIONID"),
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
        Credentials: true
        
      }
    }
  }