package com.mindwiki.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mindwiki.service.JwtService;
import com.mindwiki.service.MindService;
import com.mindwiki.service.NodeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/mindwiki/node")
public class NodeController {

@Autowired
private NodeService nodeService;

@Autowired
private MindService mindService;

@Autowired
private JwtService jwtService;

@PostMapping("/getInitNode")
public ResponseEntity<Object> getInitNode(HttpSession session,
        @RequestParam(value = "jwt", required = false) String jwt,
        @RequestParam(value = "hashtag", required = false) String hashtag,
        @RequestParam(value = "subject", required = false) String subject) throws UnsupportedEncodingException, SQLException{
    
    StringTokenizer st = new StringTokenizer(hashtag, ",");
    String[] hashtagArr = new String[3];
    int idx = 0;
    while(st.hasMoreTokens()) {
        hashtagArr[idx++] = st.nextToken();
    }
    
    return new ResponseEntity<Object>(    
            "[\r\n" + 
                    "{\"children\":\r\n" + 
                    "[{\"reason\":\"0\",\"label\":\""+hashtagArr[0] + "\"},\r\n" + 
                    "{\"reason\":\"0\",\"label\":\""+ hashtagArr[1] + "\"},\r\n" + 
                    "{\"reason\":\"0\",\"label\":\""+ hashtagArr[2] +"\"}],\r\n" + 
                    "\"root\":\"true\",\r\n" + 
                    "\"label\":\"" + subject + "\",\r\n" + 
                    "\"url\":\"\"}\r\n" + 
                    "]", HttpStatus.ACCEPTED);
}

@PostMapping("/setNode")
public ResponseEntity<Map<String, Object>> setNode(HttpSession session,
        @RequestParam(value="MindID", required=false) int MindID,
        @RequestParam(value="data", required=false) String nodeData) throws SQLException{
    
    Map<String, Object> reponse = new HashMap<>();
    
    Gson gson = new Gson();

    nodeService.setNode(gson.toJson(nodeData),MindID);

    reponse.put("message", "SUCCESS");
    return new ResponseEntity<Map<String, Object>>(reponse, HttpStatus.ACCEPTED);
}

@PostMapping("/getNode")
public ResponseEntity<Object> getNode(HttpSession session,
        @RequestParam(value="MindID", required=false) int MindID) throws SQLException{
    
//        NodeDto nodeDto = new NodeDto();
//        nodeDto.setMindID(MindID);

//        Object obj = nodeService.getNode(nodeDto).getNodeDto().getNodeObject();
        Object obj = nodeService.getNode(MindID);
        Gson gson = new Gson();
        return new ResponseEntity<Object>(obj, HttpStatus.ACCEPTED);
    }

}