#!/bin/bash

psql -U postgres -d 2025-10-quartz -f database/init_postgres.sql
