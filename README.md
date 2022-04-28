## Basic-RBAC-Sample

Basic role based access control system with sample biz system

### common
Common used tools including 
- annotation
- aspects
- configurations
  - mybatis config: pagination, auto populated audit fields
  - shiro config: fulfill JWT and Custom realms
- base controller
  - getSubject
  - getCurrentUser
- base exceptions
- models
  - base entity
  - TreeModel
  - APIResponse: unified api response
  - PageResponseDTO: unified page results structure
- properties
  - GlobalConstant: constants
  - ShrioProperties
  - SYSProperties: common used configurable properties
- utils
  - common used utils

## system
Role based controll system core, including:
- Department management
- Role management
- Account management
- Dictionary management
- Menu Management: including frontend routes and privileges
- Login/Logout

## biz
Sample biz system:
- Job: Involve XXL-JOB to deal with scheduler jobs

## How to run

1. checkout codes and switch to billing branch
> git clone https://github.com/superfrankiefan/simple-rbac-template.git

> git checkout -b billing

2. initial mysql5.7 database
- import data/dump-rbac-202204261006.sql into your local database
- change mysql configuration properties

3. start application
> mvn clean install

  run application