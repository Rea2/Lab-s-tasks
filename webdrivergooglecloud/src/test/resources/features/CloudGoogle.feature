Feature: Cloud Google From
  As a user
  Background:
    Given I opened Calculator Engine tab
    When I submitted filled Calculator's Engine with parameters:
      | Number of instances         | 4                                                               |
      | Operating System / Software | Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS |
      | VM Class                    | Regular                                                         |
      | Instance type               | n1-standard-8    (vCPUs: 8, RAM: 30 GB)                         |
      | Add GPUs                    | true                                                            |
      | Number of GPUs              | 1                                                               |
      | GPU type                    | NVIDIA Tesla V100                                               |
      | Local SSD                   | 2x375 GB                                                        |
      | Data center location        | Frankfurt (europe-west3)                                        |
      | Committed Usage             | 1 Year                                                          |

  Scenario: Giving correct estimate from after submitting data
    Then Data on the estimate form is the same as was submitted

  Scenario: Giving Total estimate cost
    Then Estimate form contains the expected price

  Scenario: I get total estimate cost on the email
    When I submit filled Email Estimate Form with specified email address
    Then I receive email with  total estimate cost on the specified email address









