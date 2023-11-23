SERVICE_NAME = binary_clock
INSTALL_DIR = /usr/local/bin
SERVICE_DIR = /etc/systemd/system
MAN_DIR = /usr/share/man/man1

build: install install-service install-man
	@echo "Build completed."

install: 
	@if [ -f "$(INSTALL_DIR)/$(SERVICE_NAME)" ]; then \
		echo "$(INSTALL_DIR)/$(SERVICE_NAME) already exists. Cannot install again."; \
	else \
		cp binary_clock.py $(INSTALL_DIR)/$(SERVICE_NAME); \
		chmod +x $(INSTALL_DIR)/$(SERVICE_NAME); \
		echo "Installed $(SERVICE_NAME) to $(INSTALL_DIR)/$(SERVICE_NAME)"; \
	fi

start:
	python3 binary_clock.py

start-service:
	sudo systemctl start $(SERVICE_NAME).service

stop-service:
	sudo systemctl stop $(SERVICE_NAME).service

install-service:
	@if [ -f "$(SERVICE_DIR)/$(SERVICE_NAME).service" ]; then \
		echo "$(SERVICE_DIR)/$(SERVICE_NAME).service already exists. Cannot install again."; \
	else \
		cp $(SERVICE_NAME).service $(SERVICE_DIR)/$(SERVICE_NAME).service; \
		systemctl daemon-reload; \
		systemctl enable $(SERVICE_NAME).service; \
		echo "Service installed..."; \
	fi

install-man:
	@if [ -f "$(MAN_DIR)/$(SERVICE_NAME).1" ]; then \
		echo "Manual already installed"; \
	else \
		cp $(SERVICE_NAME).1.gz $(MAN_DIR)/; \
		mandb; \
		echo "Manual installed..."; \
	fi

clean:
	rm $(INSTALL_DIR)/$(SERVICE_NAME); \
	rm $(SERVICE_DIR)/$(SERVICE_NAME).service; \
	rm $(MAN_DIR)/$(SERVICE_NAME).1.gz; \
	mandb

help:
	@echo "Commands:"
	@echo "  build			- Installs script and service in one go"
	@echo "  install                - Installs $(SERVICE_NAME) to $(INSTALL_DIR)"
	@echo "  start                  - Starts $(SERVICE_NAME)"
	@echo "  start-service          - Starts systemd service"
	@echo "  stop-service           - Stops systemd service"
	@echo "  install-service        - Install systemd service"
	@echo "  help                   - Display commands"

.DEFAULT_GOAL := help
