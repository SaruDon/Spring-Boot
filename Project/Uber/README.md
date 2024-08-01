
# Uber Backend Clone
In this project, I developed an enterprise-level application with the best industry-level practices to build an end-to-end application that allows users to book a ride.


## Low-Level System Design

  ![Screenshot from 2024-08-01 10-35-43](https://github.com/user-attachments/assets/e3c5db49-8516-46fc-bdb7-31a11165a4fe)

User can be 
- Rider (Default)
- Driver (Admin can onboard Driver)
- Admin

## UML Diagram Uber

![Screenshot from 2024-08-01 10-55-08](https://github.com/user-attachments/assets/07e42682-7fdb-4092-9457-de9e7ebf025f)

## API Reference

#### Get all routes

```http
  GET/{service}/{version}/{profile}/{coordinates}[.{format}]?option=value&option=value
```

| Parameter | Description                |
| :-------- | :------------------------- |
| `service` | One of the following values: route , nearest , table , match , trip , tile |
| `version` |Version of the protocol implemented by the service. v1 for all OSRM 5.x installations |
| `profile` | Mode of transportation, is determined statically by the Lua profile that is used to prepare the data using osrm-extract . Typically car , bike or foot if using one of the supplied profiles. |
| `coordinates` | String of format {longitude},{latitude};{longitude},{latitude}[;{longitude},{latitude} ...] or polyline({polyline}) or polyline6({polyline6}) . |
| `format` | json or flatbuffers . This parameter is optional and defaults to json . |





