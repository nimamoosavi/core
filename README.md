### Cost frameworks

- Support interfaces must be implement in other libraries;
- Message and Objects protocol, this rules make standards in cost project
- plan to warning messages like this api remove in next version


# Interfaces
+ log protocol

#Warning
add warning to all responce and add notifies
####Java

```java
	MethodSignature signature = (MethodSignature) joinPoint.getSignature();
				Method method = signature.getMethod();
				Warnings annotation = method.getAnnotation(Warnings.class);
				Warning[] warnings = annotation.warnings();
				if (warnings.length > 0) {
					List<Notification> warning = applicationException.createApplicationWarning(warnings);
					ResponseEntity<BaseDTO<Object>> responseEntity = (ResponseEntity<BaseDTO<Object>>) result;
					if (null != responseEntity && responseEntity.getBody() != null) {
						List<Notification> list = responseEntity.getBody().getNotifies();
						if (list != null)
							list.addAll(warning);
						else
							responseEntity.getBody().setNotifies(warning);
					}
				}

```