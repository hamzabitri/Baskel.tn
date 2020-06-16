<?php

namespace BaskelBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
/**
 * CategorieP
 *
 * @ORM\Table(name="categoriep")
 * @ORM\Entity(repositoryClass="BaskelBundle\Repository\CategoriePRepository")
 *  * @UniqueEntity(
 *     fields={"name"},
 *     errorPath="name",
 *     message="This name is already in use."
 * )
 */
class CategorieP
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_cp", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="name", type="string", length=10)
     *      *      * @Assert\Length(
     *      min = 2,
     *      max = 100,
     *      minMessage = "the reference must be  {{ limit }} characters long",
     *      maxMessage = "the reference must be  {{ limit }} characters long"
     *     )
     */
    private $name;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set name
     *
     * @param string $name
     *
     * @return CategorieP
     */
    public function setName($name)
    {
        $this->name = $name;

        return $this;
    }

    /**
     * Get name
     *
     * @return string
     */
    public function getName()
    {
        return $this->name;
    }
    public function __toString() {
        return $this->name;
    }
}

