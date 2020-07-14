package ir.webold.framework.controller;

import io.swagger.annotations.*;
import ir.webold.framework.domain.dto.BaseDTO;
import ir.webold.framework.domain.entity.PropertyValue;
import ir.webold.framework.domain.viewmodel.propertyvalue.request.PropertyValueReqVM;
import ir.webold.framework.domain.viewmodel.propertyvalue.response.PropertyValueResVM;
import ir.webold.framework.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/propertyvalue")
@Api(value = "PropertyValue", protocols = "HTTP", description = "سرویس مدیریت ویژگی ها")
class PropertyValueController extends BaseController<PropertyValue, PropertyValueReqVM, PropertyValueResVM, Long> {
    @Autowired
    PropertyValueService propertyValueService;

    @ApiOperation(value = "${getByParentCodeValue}", notes = "${getByParentCodeNote}")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "{'data':null , 'resultCode' : 5012 , 'resultMessage': '....' , 'status':'ERROR'}")})
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "accessToken", required = true, dataType = "string", paramType = "header")})
    @GetMapping(value = "/byparentcode")
    public ResponseEntity<BaseDTO<List<PropertyValueResVM>>> getByParentCode(@RequestParam String parentCode) {
        return new ResponseEntity<>(propertyValueService.getByParentCode(parentCode), HttpStatus.OK);
    }

    @ApiOperation(value = "${getByCodeValue}", notes = "${getByCodeNote}")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "{'data':null , 'resultCode' : 5012 , 'resultMessage': '....' , 'status':'ERROR'}")})
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "accessToken", required = true, dataType = "string", paramType = "header")})
    @GetMapping(value = "/bycode")
    public ResponseEntity<BaseDTO<PropertyValueResVM>> getByCode(@RequestParam String code) {
        return new ResponseEntity<>(propertyValueService.getByCode(code), HttpStatus.OK);
    }

    @ApiOperation(value = "${getAllParentValue}", notes = "${getAllParentNote}")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "{'data':null , 'resultCode' : 5012 , 'resultMessage': '....' , 'status':'ERROR'}")})
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "accessToken", required = true, dataType = "string", paramType = "header")})
    @GetMapping(value = "/parents")
    public ResponseEntity<BaseDTO<List<PropertyValueResVM>>> getAllParent() {
        return new ResponseEntity<>(propertyValueService.getAllParent(), HttpStatus.OK);
    }
}
